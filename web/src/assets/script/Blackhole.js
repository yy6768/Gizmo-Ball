import { GimzoComponent } from "./GimzoComponent";

export class Blackhole extends GimzoComponent{

    constructor(gameMap, c, r){
        super(gameMap, c, r);

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.black_hole_icon;
    }


    update(){
        this.render();
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L, this.r * L, L , L);
    }

}