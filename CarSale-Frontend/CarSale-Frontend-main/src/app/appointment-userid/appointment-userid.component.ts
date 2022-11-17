import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';

@Component({
  selector: 'app-appointment-userid',
  templateUrl: './appointment-userid.component.html',
  styleUrls: ['./appointment-userid.component.css']
})
export class AppointmentUseridComponent implements OnInit {
  appointmentsModel!: AppointmentModel[];
  constructor(private appointmentServiceService:AppointmentServiceService, 
    private router:Router) { }

    id:number=39;
    private getAppointment(){
      this.appointmentServiceService.findAppointmentByUserID(this.id).subscribe(data=>{
        this.appointmentsModel=data;
      });
    };  

    updateAppointment(id:number,clickedBy:string){
      if(clickedBy==="user"){
      this.router.navigate(['updateAppointment',id]);
      }else{
        this.router.navigate(['updateAppointment',id]);
      }
    }
    // appointmentdetails(id:number){
    //   this.router.navigate(['appointment-details',id]);
    // }
  
    // OpenAppointment(){
    //   this.router.navigate(['Openappointments']);
    // }
    
    AddAppointment(){
      this.router.navigate(['addAppointment']);
    }
  
    // goBackToDashboard(){
    //   this.router.navigate(['dashboard']);
    // }
    
    deleteAppointment(id:number){
      this.appointmentServiceService.deleteAppointment(id).subscribe(data=>{
        console.log(data);
        this.getAppointment();
      },error => console.log(error));
    }
  

  ngOnInit(): void {
    this.getAppointment();
  }

}
