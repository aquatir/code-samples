<h4> About </h4>

An example implementation of JWT token authentication with springboot and angular 5
with access and refresh tokens.

<h4> IMPORTANT NOTES <h4>

Read up on access/refresh tokens and how they work before using this code!

Refresh tokens are mean to be 
1) Revocable
2) Should not be usable without client id and/or secret

For better access/refresh token implementation ideas please read up on implementation details for following protocols:
* OAuth
* OpenID

<h4> Lauching project </h4>

1) Navigate to backend dir <br>
```cd code-sample-angular-kotlin/code-sample-jwt-token-example/kotlin-backend```
2) Launch backend <br>
``` ./gradlew bootRun ```
3) In another console navigate to frontend dir <br>
```cd code-sample-angular-kotlin/code-sample-jwt-token-example/angular-frontend```
4) Launch frontend <br>
``` ng serve --open```