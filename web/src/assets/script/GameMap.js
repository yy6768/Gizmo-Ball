import { BallObject } from "./BallObject";
import { Ball } from './Ball'
import { Circle } from "./Circle";

export class GameMap extends BallObject {
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0; //块的大小
        this.rows = 20;
        this.cols = 20;

        this.ball = null;
        this.circles = [];
    }

    start(){
        this.add_listening_events();
    }

    //获取当前鼠标位置
    getEventPosition(event){
        // console.log(this.L);
        let x = Math.floor(event.offsetX / this.L);
        let y = Math.floor(event.offsetY / this.L);
        return [x,y];
    }   
    
    /*
      在地图添加组件
      1、判别类别
      2、创建对象
      3、向后端发送消息
    */
    add(p){
        let type = this.store.state.layout.gameObject;
        // console.log(type);
        if(type === 'ball'){
            //球体只能有一个
            if(this.ball !== null) {
                console.log("destroy");
                this.ball.destroy();
            }
            this.ball = new Ball(this,p[0],p[1]);
        } else if(type === 'circle'){
            let id = this.circles.length;
            this.circles.push(new Circle(this,id,p[0],p[1]));
        }
 
        this.store.state.layout.socket.send("add "+ type + " " + p[0] + " " + p[1]);
    }



    //绑定事件
    add_listening_events(){ 
        if(this.store.state.layout.status === 'layout'){ //游玩模式绑定点击事件
           
            this.ctx.canvas.addEventListener("click",e =>{
                let p = this.getEventPosition(e);
                this.add(p);
            });
        } 
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {
        const color_even = "#AAD751", color_odd = "#A2D149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
}