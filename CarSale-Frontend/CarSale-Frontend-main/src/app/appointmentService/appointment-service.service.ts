import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppointmentModel } from '../appointmentModel/appointment-model';

@Injectable({
  providedIn: 'root'
})
export class AppointmentServiceService {
  private getURL="http://localhost:8080/appointment/appointments";
  private postURL="http://localhost:8080/appointment/addAppointment";
  private putURL="http://localhost:8080/appointment/updateAppointment";
  private deleteURL="http://localhost:8080/appointment/delete";
  private getOpenURL="http://localhost:8080/appointment/openAppointments";
  private getUserAppointmentURL="http://localhost:8080/appointment/UserAppointment";
  constructor(private httpClient:HttpClient) { }

  getAppointmentList():Observable<AppointmentModel[]>{
    return this.httpClient.get<AppointmentModel[]>(this.getURL);
  }

  addAppointment(appointmentmodel: AppointmentModel):Observable<Object>{
    return this.httpClient.post(this.postURL,appointmentmodel);
  }

  getAppointmentById(id:number):Observable<AppointmentModel>{
    return this.httpClient.get<AppointmentModel>(this.getURL+'/'+id);
  }

  updateAppointment(id:number,appointmentmodel: AppointmentModel):Observable<Object>{
    return this.httpClient.put(this.putURL+'/'+id,appointmentmodel);
  }

  deleteAppointment(id:number):Observable<Object>{
    return this.httpClient.delete(this.deleteURL+'/'+id);
  }

  findAppointmentByUserID(id:number):Observable<AppointmentModel[]>{
    return this.httpClient.get<AppointmentModel[]>(this.getUserAppointmentURL+'/'+id);
  }

  openAppointment():Observable<AppointmentModel[]>{
    return this.httpClient.get<AppointmentModel[]>(this.getOpenURL);
  }
}
