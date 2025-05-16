import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { ViewProductComponent } from './components/product/view/view.component';
import { CreateProductComponent } from './components/product/create/create.component';
import { UpdateProductComponent } from './components/product/update/update.component';
import { ViewCategoryComponent } from './components/category/view/view.component';
import { CreateCategoryComponent } from './components/category/create/create.component';
import { UpdateCategoryComponent } from './components/category/update/update.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: AdminComponent,
  },

  {
    path: 'product',
    children: [
      { path: '', component: ViewProductComponent },
      { path: 'create', component: CreateProductComponent },
      { path: ':id', component: UpdateProductComponent },
    ],
  },
  {
    path: 'category',
    children: [
      { path: '', component: ViewCategoryComponent },
      { path: 'create', component: CreateCategoryComponent },
      { path: ':id', component: UpdateCategoryComponent },
    ],
  },
  {
    path: 'components',
    component: AdminComponent,
  },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: 'error/404' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
