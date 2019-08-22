import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, PageEvent } from '@angular/material';
import { AdminService } from '../admin.service';
import { CustomPaginationService } from 'src/app/pagination/services/custom-pagination.service';
import { Page } from 'src/app/core/model/page';
import { User } from 'src/app/core/model/User';
import { UserDto } from 'src/app/core/model/UserDto';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'details'];

  page: Page<UserDto> = new Page();

  dataSource = new MatTableDataSource<UserDto>();

  constructor(
    private adminService: AdminService,
    private paginationService: CustomPaginationService
  ) { }

  ngOnInit() {

    this.getData();
  }

  private getData(): void {
    this.adminService.getUsers(this.page.pageable.pageNumber, this.page.pageable.pageSize)
      .subscribe(page => {
        this.page = page;
        this.dataSource.data = this.page.content;
        console.log(this.page.content);
      });
  }

  public getNextPage(): void {
    this.page.pageable = this.paginationService.getNextPage(this.page);
    this.getData();
  }

  public getPreviousPage(): void {
    this.page.pageable = this.paginationService.getPreviousPage(this.page);
    this.getData();
  }

  public getPageInNewSize(pageSize: number): void {
    this.page.pageable = this.paginationService.getPageInNewSize(this.page, pageSize);
    this.getData();
  }




}
