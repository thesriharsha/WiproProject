import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByCityComponent } from './search-by-city.component';

describe('SearchByCityComponent', () => {
  let component: SearchByCityComponent;
  let fixture: ComponentFixture<SearchByCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchByCityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchByCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
