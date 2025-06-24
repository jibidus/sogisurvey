package com.sogilis.survey

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension


class ResponseRepositoryTest {

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
        val response = Response(
            author = "author",
            priorities = setOf(
                Priority(
                    criterionId = "A",
                    value = 50,
                    comment = "test comment"
                )
            ),
            comment = "global comments",
        )
        repository.save(response)
        Assertions.assertEquals(1, repository.count())
    }

    @Test
    fun `save() without command`() {
        val response = Response(
            author = "author",
            priorities = setOf(
                Priority(
                    criterionId = "A",
                    value = 50,
                    comment = null
                )
            ),
            comment = "global comments",
        )
        repository.save(response)
        Assertions.assertEquals(1, repository.count())
    }

    @Test
    fun `save() override previous result`() {
        val response = Response(
            author = "author",
            priorities = setOf(
                Priority(
                    criterionId = "A",
                    value = 50,
                    comment = "test comment"
                )
            ),
            comment = "global comments",
        )
        repository.save(response)
        repository.save(response)
        Assertions.assertEquals(1, repository.count())
    }
}