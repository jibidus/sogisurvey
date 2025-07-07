
db-dump:                                                                        
	heroku pg:backups:capture --app sogisurvey                                  
	heroku pg:backups:download --app sogisurvey                                 
	pg_restore --verbose --clean --no-acl --no-owner -h localhost -d jibidus latest.dump

