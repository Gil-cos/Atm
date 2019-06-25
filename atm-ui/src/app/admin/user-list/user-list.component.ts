import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { UserResponse } from 'src/app/core/model/UserResponse';
import { UserRegisterService } from '../user-register/user-register.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'details'];

  dataSource = new MatTableDataSource<UserResponse>();

  constructor(
    private userService: UserRegisterService
  ) { }

  ngOnInit() {

    this.userService.getUsers()
      .subscribe(users => this.dataSource.data = users);
  }

}
