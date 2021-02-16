import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorScanListComponent } from './error-scan-list.component';

describe('ErrorScanListComponent', () => {
  let component: ErrorScanListComponent;
  let fixture: ComponentFixture<ErrorScanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrorScanListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorScanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
