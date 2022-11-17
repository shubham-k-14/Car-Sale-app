import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector:'nav-bar',
    templateUrl:'navbar.component.html'
})
export class NavbarComponent implements OnInit{
    public href: string = "";
    public var:string[]=[];
  
    constructor(private router:Router){

    }
    ngOnInit(){
       
    }
    isNavbarNedded():boolean{
        this.href=window.location.href;
        this.var=this.href.split('/');
        if(this.var[3] && this.var[3]=='admin'){
            return false;
        }
        return true;
    }
}