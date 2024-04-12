import * as THREE from 'three'

export default class Base{
    constructor(canvas) {
        this.scene = new THREE.Scene()
        this.camera = new THREE.PerspectiveCamera(
            75,
            window.innerWidth/window.innerHeight,
            0.001,
            1000
        )
        this.camera.position.z = 100000
        this.camera.updateProjectionMatrix()


        this.renderer = new THREE.WebGLRenderer({canvas,antialias:true})
        this.renderer.setSize(window.innerWidth , window.innerHeight)
        this.renderer.setPixelRatio(window.devicePixelRatio)
    }
    update(){
        this.renderer.render(this.scene,this.camera)
    }

    addAmbientLight(color='0xffffff',intensity=4){
        let light = new THREE.AmbientLight(color,intensity)
        this.scene.add(light)
        return light
    }
    addAmbientLight2(color='0xffffff',intensity=3){
        let light = new THREE.AmbientLight(color,intensity)
        this.scene.add(light)
        return light
    }
    resize(){
        this.camera.aspect = window.innerWidth/window.innerHeight
        this.camera.updateProjectionMatrix()
    }
    changeSize(width,height){
        this.renderer.setSize(width,height)
    }
}