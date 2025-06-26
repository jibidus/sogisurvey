package com.sogilis.survey

import org.jooq.impl.DSL.count
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ResponsesRepositoryTest {
    companion object {
        @JvmField
        @RegisterExtension
        var database = DbExtension()
    }

    @BeforeEach
    fun setupDb() = resetDatabase(database.connection)

    private val repository = ResponsesRepository(database.connection)

    @Test
    fun save() {
        val response =
            Response(
                author = "author",
                priorities =
                    setOf(
                        Priority(
                            criterionId = "A",
                            value = 50,
                            comment = "test comment",
                        ),
                    ),
                comment = "global comments",
            )
        repository.save(response)
        assertEquals(1, repository.count())
    }

    @Test
    fun `save() without comment`() {
        val response =
            Response(
                author = "author",
                priorities =
                    setOf(
                        Priority(
                            criterionId = "A",
                            value = 50,
                            comment = null,
                        ),
                    ),
                comment = null,
            )
        repository.save(response)
        assertEquals(1, repository.count())
    }

    @Test
    fun `save() overrides previous response`() {
        val response =
            Response(
                author = "author",
                priorities =
                    setOf(
                        Priority(
                            criterionId = "A",
                            value = 50,
                            comment = "test comment",
                        ),
                    ),
                comment = "global comments",
            )
        repository.save(response)
        repository.save(response)
        assertEquals(1, repository.count())
    }

    @Test
    fun `save() escape quotes in comments`() {
        val response =
            Response(
                author = "author",
                priorities =
                    setOf(
                        Priority(
                            criterionId = "A",
                            value = 50,
                            comment = "priority ' comment",
                        ),
                    ),
                comment = "global ' comment",
            )
        repository.save(response)
        val storedGlobalComment =
            database.dsl
                .select(ResponsesTable.comment)
                .from(ResponsesTable.NAME)
                .fetch()
                .single()
                .value1()
        assertEquals("global ' comment", storedGlobalComment)
        val storedPriorityComment =
            database.dsl
                .select(PrioritiesTable.comment)
                .from(PrioritiesTable.NAME)
                .fetch()
                .single()
                .value1()
        assertEquals("priority ' comment", storedPriorityComment)
    }

    @Test
    fun `save() with many priorities`() {
        val response =
            Response(
                author = "author",
                priorities =
                    setOf(
                        Priority(
                            criterionId = "A",
                            value = 50,
                            comment = "one comment",
                        ),
                        Priority(
                            criterionId = "B",
                            value = 10,
                            comment = "other comment",
                        ),
                    ),
                comment = "global comments",
            )
        repository.save(response)
        val prioritiesCount =
            database.dsl
                .select(count())
                .from(PrioritiesTable.NAME)
                .fetch()
                .single()
                .value1()
        assertEquals(2, prioritiesCount)
    }
}
