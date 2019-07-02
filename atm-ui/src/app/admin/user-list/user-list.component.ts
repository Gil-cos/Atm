import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, PageEvent } from '@angular/material';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'details'];

  dataSource = new MatTableDataSource<Object>();

  PageEvent: PageEvent;
  length = 0;
  pageIndex = 0;
  pageSize = 3;
  previousPageIndex = 0;

  @ViewChild(MatPaginator, {}) paginator: MatPaginator;

  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {

    this.adminService.getUsers(this.pageIndex, this.pageSize)
      .subscribe((users) => {
        this.dataSource.data = users['content'];
        this.length = users['totalElements'];
      });
    this.dataSource.paginator = this.paginator;
  }

  onPageChange(e: PageEvent) {
    this.pageIndex = e.pageIndex;
    this.adminService.getUsers(e.pageIndex, this.pageSize)
      .subscribe((users) => {
        this.dataSource.data = users['content'];
      });
  }



}
