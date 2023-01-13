import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCitiesPollutionComponent } from './list-cities-pollution.component';

describe('ListCitiesPollutionComponent', () => {
  let component: ListCitiesPollutionComponent;
  let fixture: ComponentFixture<ListCitiesPollutionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCitiesPollutionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCitiesPollutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
