import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppointmentModel } from '../appointmentModel/appointment-model';
import { AppointmentServiceService } from '../appointmentService/appointment-service.service';

@Component({
  selector: 'app-appointment-details',
  templateUrl: './appointment-details.component.html',
  styleUrls: ['./appointment-details.component.css']
})
export class AppointmentDetailsComponent implements OnInit {

  id!: number;
  appointmentsModel!: AppointmentModel;
  constructor(private route:ActivatedRoute,
    private appointmentServiceService:AppointmentServiceService) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.appointmentsModel=new AppointmentModel();
    this.appointmentServiceService.getAppointmentById(this.id).subscribe(data=>{
      this.appointmentsModel=data;
    });
  }

}
