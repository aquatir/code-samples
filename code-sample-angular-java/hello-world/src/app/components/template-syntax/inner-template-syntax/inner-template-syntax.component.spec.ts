import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InnerTemplateSyntaxComponent } from './inner-template-syntax.component';

describe('InnerTemplateSyntaxComponent', () => {
  let component: InnerTemplateSyntaxComponent;
  let fixture: ComponentFixture<InnerTemplateSyntaxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InnerTemplateSyntaxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InnerTemplateSyntaxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
