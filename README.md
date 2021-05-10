# BingeWatachDbAssignment
Assignemt of HuEx for APi and Relational Db.

Documented everything on swagger - http://localhost:8080/swagger-ui/index.html#/ APIs can directly be hit from the UI link mentined above

1. AspectJ package is for tracking execution time where one annotation has been created.
2. Logger level is set to info
3. Config package has configruations for Batch and swagger
4. one controller package has the controller for Apis
5. filter package has a common filter to send response headers accross all apis
6. listener is the package where classes are written to verify completion of batch jobs
7. processor package contains itemProcessor for batch job
8. security pakage contains classes for header based authentication
9. service package has interface and impl class for the filtering service
10. This is the cred : spring.security.user.name=username spring.security.user.password=password spring.security.user.role=USER netflix.http.auth.tokenName=X-Auth-Token           netflix.http.auth.tokenValue=abcd123456
11. Few enchancements are required to the current commit
