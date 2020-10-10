package com.codesample.ktor

import java.lang.RuntimeException

class ServerException(msg: String): RuntimeException(msg)
