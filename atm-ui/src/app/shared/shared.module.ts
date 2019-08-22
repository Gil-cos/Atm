import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ShowIfLoggedModule } from './directives/show-if-logged/show-if-logged.module';
import { PaginationModule } from '../pagination/pagination.module';

@NgModule({
  declarations: [],
  exports: [
    MaterialModule,
    FlexLayoutModule,
    ShowIfLoggedModule
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    ShowIfLoggedModule
  ]
})
export class SharedModule { }
