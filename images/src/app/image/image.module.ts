import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImageItemComponent } from './components/image-item/image-item.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ImageContainerComponent} from "./components/image-container/image-container.component";
import { GalleryComponent } from './components/gallery/gallery.component';
import {AppRoutingModule} from "../app-routing.module";
import {ImageRoutingModule, routingComponents} from "./image-routing.module";

@NgModule({
  declarations: [
    ImageItemComponent,
    routingComponents,

  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ImageRoutingModule
  ],
  exports: [
    ImageContainerComponent,
    GalleryComponent
  ]
})
export class ImageModule { }
