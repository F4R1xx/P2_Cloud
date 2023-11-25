import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompositorModule } from 'src/app/model/compositor/compositor.module';
import { CompositorService } from 'src/app/services/compositor.service';

@Component({
  selector: 'app-compositor',
  templateUrl: './compositor.component.html',
  styleUrls: ['./compositor.component.css']
})
export class CompositorComponent implements OnInit {
  compositors: CompositorModule[] = [];

  constructor(private compositorService: CompositorService, private router: Router) { }

  ngOnInit(): void {
    this.compositorService.getCompositor().subscribe(response => {
      this.compositors = response as any;
    });
  }

  redirectToDetail(id: any){
    this.router.navigate(["detail", id]);
  }

}
