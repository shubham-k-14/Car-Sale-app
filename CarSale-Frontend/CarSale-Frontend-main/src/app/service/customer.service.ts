import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.entity';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  public addCustomer(customer: Customer) {
    return this.http.post(
      'http://localhost:7171/customer/addCustomer',
      customer
    );
  }

  public getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(
      'http://localhost:7171/customer/getAllCustomers'
    );
  }

  public getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(
      `http://localhost:7171/customer/findCustomerById/${id}`
    );
  }

  public removeCustomer(id: number): Observable<Customer> {
    return this.http.delete<Customer>(
      `http://localhost:7171/customer/removeCustomer/${id}`
    );
  }

  public updateCustomer(id: number, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(
      `http://localhost:7171/customer/updateCustomer/${id}`,
      customer
    );
  }

  public getCustomerByLocation(city: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(
      `http://localhost:7171/customer//findCustomersByLocation/${city}`
    );
  }
}
