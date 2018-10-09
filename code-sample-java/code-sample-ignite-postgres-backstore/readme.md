### Ignite and Postgres example

#### About
Demonstrate usage of postgres DB with ignite as middle in-memory cache. Ignite should load all data from postgres
on startup and then allow using this in-memory data for computation (not yet implemented).

Note: Ignite write a lot of really good logs telling you about errors / performance problems / etc on node start. 
Don't forget to read them up before launching in production!
