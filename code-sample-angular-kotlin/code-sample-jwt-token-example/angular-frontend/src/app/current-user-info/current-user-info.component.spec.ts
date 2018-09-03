import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentUserInfoComponent } from './current-user-info.component';

describe('CurrentUserInfoComponent', () => {
  let component: CurrentUserInfoComponent;
  let fixture: ComponentFixture<CurrentUserInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentUserInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
