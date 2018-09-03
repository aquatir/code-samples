export class BACKEND_URLS {
  private static API_BASE: string = "http://localhost:8080";

  public static AUTH: string = BACKEND_URLS.API_BASE + "/auth";
  public static AUTH_REFRESH: string = BACKEND_URLS.API_BASE + "/auth/refresh";

  public static NO_AUTH_DATA: string = BACKEND_URLS.API_BASE + "/data";
  public static USER_AUTH_DATA: string = BACKEND_URLS.API_BASE + "/userData";
  public static ADMIN_AUTH_DATA: string = BACKEND_URLS.API_BASE + "/adminData";
  public static USER_OR_ADMIN_AUTH_DATA: string = BACKEND_URLS.API_BASE + "/userOrAdminData";
}
