export class Level{
    constructor(){
    }

    setup() {
        this.level = 1;
        switch(this.level){
            case 1:
                this.character_position = new THREE.Vector3(-34,3,31);
                this.target_position = new THREE.Vector3(-28,2,-60);
                this.forward = -0.1;
                this.left = Math.PI/2250;
                this.backward = 0.1;
                this.right = -Math.PI/2250;
                this.walk_speed = 0.01;
            break;
    
            case 2:
                this.character_position = (65,0,-70);
                this.target_position = (-62,2,-3);
                this.forward = -2;
                this.left = Math.PI/50;
                this.backward = 2;
                this.right = -Math.PI/50;
                this.walk_speed = 1;
                location.reload();
            break;
    
            case 3:
                this.character_position = (65,0,-70);
                this.target_position = (-62,2,-3);
                this.forward = -2;
                this.left = Math.PI/50;
                this.backward = 2;
                this.right = -Math.PI/50;
                this.walk_speed = 0.1;
            break;
        }
    }

    next() {
        this.level += 1;
        this.setup(this.level);
    }
}