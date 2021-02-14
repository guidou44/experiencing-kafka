import { TestBed } from '@angular/core/testing';

import { ErrorScanService } from './error-scan.service';

describe('ErrorScanService', () => {
  let service: ErrorScanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorScanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
