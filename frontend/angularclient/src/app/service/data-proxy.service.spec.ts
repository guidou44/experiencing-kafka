import { TestBed } from '@angular/core/testing';

import { DataProxyService } from './data-proxy.service';

describe('DataProxyService', () => {
  let service: DataProxyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataProxyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
