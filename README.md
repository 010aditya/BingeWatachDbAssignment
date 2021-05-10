# BingeWatachDbAssignment
Assignemt of HuEx for APi and Relational Db.

Documented everything on swagger - http://localhost:8080/swagger-ui/index.html#/ APIs can directly be hit from the UI link mentined above

AspectJ package is for tracking execution time where one annotation has been created.
Config package has configruations for Batch and swagger
one controller package has the controller for Apis
filter package has a common filter to send response headers accross all apis
listener is the package where classes are written to verify completion of batch jobs
processor package contains itemProcessor for batch job
security pakage contains classes for header based authentication
service package has interface and impl class for the filtering service
This is the cred : spring.security.user.name=username spring.security.user.password=password spring.security.user.role=USER netflix.http.auth.tokenName=X-Auth-Token netflix.http.auth.tokenValue=abcd123456
Logger level is set to info
Few enchancements are required to the current commit
