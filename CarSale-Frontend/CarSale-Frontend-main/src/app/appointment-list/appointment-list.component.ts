import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';
@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointmentsModel!: AppointmentModel[];
  constructor(private appointmentServiceService:AppointmentServiceService, 
    private router:Router) { }

  ngOnInit(): void {
    this.getAppointment();
  }

  updateAppointment(id:number){
    this.router.navigate(['updateAppointment',id]);
  }
  appointmentdetails(id:number){
    this.router.navigate(['appointment-details',id]);
  }

  OpenAppointment(){
    this.router.navigate(['Openappointments']);
  }
  
  AddAppointment(){
    this.router.navigate(['addAppointment']);
  }

  goBackToDashboard(){
    this.router.navigate(['dashboard']);
  }
  
  deleteAppointment(id:number){
    this.appointmentServiceService.deleteAppointment(id).subscribe(data=>{
      console.log(data);
      this.getAppointment();
    },error => console.log(error));
  }

  private getAppointment(){
    this.appointmentServiceService.getAppointmentList().subscribe(data=>{
      this.appointmentsModel=data;
    });
  };

}
