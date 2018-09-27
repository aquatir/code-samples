package codesample.kotlin.graphql.config

import org.springframework.context.annotation.Configuration

/**
 * There are some things you should/may set when working with graphql:
 * 1. Limit query depth if at least one circular dependency exists in your domain
 * 2. Limit query complexity if some your queries can be too big
 * 3. Query timeouts
 * 4. Throttling / Rate limits
 */
@Configuration
class GraphQLConfig