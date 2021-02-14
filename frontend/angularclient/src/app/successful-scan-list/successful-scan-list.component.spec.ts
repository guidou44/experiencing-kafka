import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfulScanListComponent } from './successful-scan-list.component';

describe('SuccessfulScanListComponent', () => {
  let component: SuccessfulScanListComponent;
  let fixture: ComponentFixture<SuccessfulScanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessfulScanListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfulScanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
