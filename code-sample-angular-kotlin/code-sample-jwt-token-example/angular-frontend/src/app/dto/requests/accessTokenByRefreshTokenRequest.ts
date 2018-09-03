// Get new access token using valid refresh token
export class AccessTokenByRefreshTokenRequest {
  constructor(refreshToken: string) {
    this.refreshToken = refreshToken;
  }
  refreshToken: string;
}
