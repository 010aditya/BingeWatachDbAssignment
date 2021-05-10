# BingeWatachDbAssignment
Assignemt of HuEx for APi and Relational Db.


Documented everything on swagger - http://localhost:8080/swagger-ui/index.html#/
APIs can directly be hit from the UI link mentined above 

1. AspectJ package is for tracking execution time where one annotation has been created.
2. Config package has configruations for Batch and swagger
3. one controller package has the controller for Apis
4. filter package has a common filter to send response headers accross all apis
5. listener is the package where classes are written to verify completion of batch jobs
6. processor package contains itemProcessor for batch job
7. security pakage contains classes for header based authentication
8. service package has interface and impl class for the filtering service
9. This is the cred :  spring.security.user.name=username
spring.security.user.password=password
spring.security.user.role=USER
netflix.http.auth.tokenName=X-Auth-Token
netflix.http.auth.tokenValue=abcd123456
10. Logger level is set to info

Few enchancements are required to the current commit
