import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserManipulationButtonsComponent } from './user-manipulation-buttons.component';

describe('UserManipulationButtonsComponent', () => {
  let component: UserManipulationButtonsComponent;
  let fixture: ComponentFixture<UserManipulationButtonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserManipulationButtonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserManipulationButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
