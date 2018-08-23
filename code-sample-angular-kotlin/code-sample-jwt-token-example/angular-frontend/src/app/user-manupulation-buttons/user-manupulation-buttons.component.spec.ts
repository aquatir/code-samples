import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserManupulationButtonsComponent } from './user-manupulation-buttons.component';

describe('UserManupulationButtonsComponent', () => {
  let component: UserManupulationButtonsComponent;
  let fixture: ComponentFixture<UserManupulationButtonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserManupulationButtonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserManupulationButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
