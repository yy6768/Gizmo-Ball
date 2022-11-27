import { GimzoComponent } from "./GimzoComponent";
import { Cell } from "./Cell"
export class Baffle extends GimzoComponent{

    constructor(gameMap, c, r, isLeft){
        super(gameMap, c, r);
        
        this.cells.push(new Cell(r, c + 1));
        this.direction = 0;
        this.isLeft = isLeft;
        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.baffle_icon;
    }

    set_position(x, y) {
        this.r = y;
        this.c = x; 
    }

    //绑定键盘
    add_listening_events(){
        const store = this.gameMap.store;
        const canvas = this.gameMap.ctx.canvas;
        let socket = this.gameMap.store.state.layout.socket;
        canvas.addEventListener("keydown", e=>{
            if(store.state.layout.status === 'game'){
                if(this.isLeft){
                    console.log(e);
                    if(e.key === 'a'){
                        socket.send("left " + this.id + " -1");
                    } else if(e.key === 'd'){
                        socket.send("left " + this.id + " 1");
                    }
                } else {
                    if(e.key ==='ArrowLeft'){
                        socket.send("right " + this.id + " -1");
                    } else if(e.key === 'ArrowRight'){
                        socket.send("right " + this.id + " 1");
                    }
                }
            }
        });   
        canvas.addEventListener("keyup",e =>{
            if(store.state.layout.status === 'game'){
                if(this.isLeft){
                    if(e.key ==='a' || e.key === 'd'){
                        socket.send("left " + this.id + " 0");
                    }
                } else {
                    if(e.key ==='ArrowLeft' || e.key === 'ArrowRight'){
                        socket.send("right " + this.id + " 0");
                    }
                }    
            }
        });
        
    }

    start(){
        super.start();
        this.add_listening_events();
    }

    update(){
        this.render();
    }
    

    on_destroy(){
        super.on_destroy();
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L ,(this.r -0.9) * L, L * 2 , L);
    }
}