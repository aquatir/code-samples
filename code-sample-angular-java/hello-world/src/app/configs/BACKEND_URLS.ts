/* TODO: Implement as DI */
export class BACKEND_URLS {
  private static API_BASE: string = "http://localhost:8000";

  public static ALL_HEROES_URL: string = BACKEND_URLS.API_BASE + "/heroes";
  public static SINGLE_HERO_URL: string = BACKEND_URLS.API_BASE + "/hero";
}
