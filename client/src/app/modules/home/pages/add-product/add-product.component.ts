import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { NgIf } from '@angular/common';
import { Product } from '@/app/data/model/product';
import { ProductService } from '@/app/data/services/product/product.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css',
})
export class AddProductComponent {
  addProductForm: FormGroup;
  productCreated = false;

  constructor(private fb: FormBuilder, private productService: ProductService) {
    this.addProductForm = this.fb.group({
      skuCode: ['', [Validators.required]],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: [0, [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.addProductForm.valid) {
      const product: Product = {
        skuCode: this.addProductForm.get('skuCode')?.value,
        name: this.addProductForm.get('name')?.value,
        description: this.addProductForm.get('description')?.value,
        price: this.addProductForm.get('price')?.value,
      };
      this.productService.create(product).subscribe((product: any) => {
        this.productCreated = true;
        this.addProductForm.reset;
      });
    } else {
      console.log('Form is not valid');
    }
  }

  get skuCode() {
    return this.addProductForm.get('skuCode');
  }

  get name() {
    return this.addProductForm.get('name');
  }

  get description() {
    return this.addProductForm.get('description');
  }

  get price() {
    return this.addProductForm.get('price');
  }
}
