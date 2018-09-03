import { TestBed, inject } from '@angular/core/testing';

import { AuthInterceptor } from './auth-interceptor.service';

describe('AuthInterceptor', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthInterceptor]
    });
  });

  it('should be created', inject([AuthInterceptor], (service: AuthInterceptor) => {
    expect(service).toBeTruthy();
  }));
});
