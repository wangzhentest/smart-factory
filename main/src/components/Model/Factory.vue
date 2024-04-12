<template>
  <div>
    <canvas ref="canvasDom" id="canvasDom"></canvas>
    <el-progress type="circle" :percentage="percentageNum" class="progress" v-if="ifShow"/>
  </div>
</template>

<script setup>
import * as THREE from 'three'
import Base from '../../utils/Base.js'
import { onMounted, ref, watch} from 'vue'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls'
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader'
import { gsap } from "gsap";
import {useRouter} from 'vue-router';

let base, controls, sprite, forklift1;
let pointTexture, textTexture, spriteMaterial;
const canvasDom = ref(null);
const rayCaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();
const ifShow = ref(true)
const percentageNum = ref(0)
let selectedSprite = null;
let mixer;
const router = useRouter();

onMounted(() => {
  base = new Base(canvasDom.value);
  controls = new OrbitControls(base.camera, base.renderer.domElement);
  controls.enablePan = false;
  base.addAmbientLight();
  loadModel();
  update();

});

const props = defineProps({
  areaWidth: {
    type: Number,
    default: 0
  },
  areaHeight: {
    type: Number,
    default: 0
  }
})

watch(() => [props.areaWidth, props.areaHeight], ([newWidth, newHeight]) => {
  base.changeSize(newWidth, newHeight)
})

function loadModel() {
  const loader = new GLTFLoader();
  loader.load(
      'models/factory.glb',
      (gltf) => {
        let model = gltf.scene;
        base.scene.add(model);
        ifShow.value = false
        mixer = new THREE.AnimationMixer(model);
        gltf.animations.forEach((clip) => {
          mixer.clipAction(clip).play();
        });
        console.log(model);
        const camera1 = gltf.cameras.find((camera) => camera.name === 'camera1');
        if (camera1) {
          base.camera = camera1;
          controls = new OrbitControls(base.camera, base.renderer.domElement);
        }
        forklift1 = model.getObjectByName("forklift1");
        videoForklift();
      },
      (xhr) => {
        percentageNum.value = Math.round(xhr.loaded / xhr.total * 100 * 10) / 10;
      },
      (error) => {
        console.error('Error loading model:', error);
      }
  );
  createSprite('workshop.png', 0, 27, 0, 2.5, 0.7, 1);
  createSprite('warehousing1.png', 55, 10, 22, 1.5, 0.5, 1);
  createSprite('warehousing2.png', 45, 8, -31, 1.5, 0.5, 1);
}


function createSprite(picName, p_x, p_y, p_z, s_x, s_y, s_z) {
  pointTexture = new THREE.TextureLoader().load('Sprites/point.png');
  spriteMaterial = new THREE.SpriteMaterial({ map: pointTexture });
  sprite = new THREE.Sprite(spriteMaterial);
  sprite.position.set(p_x, p_y - 2, p_z);
  sprite.scale.set(0.2, 0.2, 0.2);
  sprite.onClick = function () {
    if (!selectedSprite) {
      selectedSprite = this;
      updateMessageArea(picName);
    }
  };
  base.scene.add(sprite);

  textTexture = new THREE.TextureLoader().load(picName);
  spriteMaterial = new THREE.SpriteMaterial({ map: textTexture });
  sprite = new THREE.Sprite(spriteMaterial);
  sprite.position.set(p_x, p_y, p_z);
  sprite.scale.set(s_x, s_y, s_z);
  sprite.onClick = function () {
    if (!selectedSprite) {
      selectedSprite = this;
      updateMessageArea(picName);
    }
  };
  base.scene.add(sprite);
}

function update() {
  requestAnimationFrame(update);

  if (mixer) {
    mixer.update(0.016);
  }

  controls.update();
  rayCaster.setFromCamera(mouse, base.camera);
  const intersects = rayCaster.intersectObjects(base.scene.children);
  if (intersects.length > 0) {
    const clickedObject = intersects[0].object;

    if (clickedObject.onClick) {
      clickedObject.onClick();
    }
  }
  base.update();
  base.resize()
}

window.addEventListener('click', onMouseClick);

function onMouseClick(event) {
  mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
  mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
  selectedSprite = null;
}

function videoForklift() {
  moveForklift_z(10, 4);
  gsap.delayedCall(4, () => {
    rotationForklift(2, 2);
    gsap.delayedCall(2, () => {
      moveForklift_x(60, 10);
      gsap.delayedCall(10, () => {
        rotationForklift(2, 2);
        gsap.delayedCall(2, () => {
          moveForklift_z(-40, 7);
          gsap.delayedCall(7, () => {
            rotationForklift(2, 2);
            gsap.delayedCall(2, () => {
              moveForklift_x(-60, 10);
              gsap.delayedCall(10, () => {
                rotationForklift(2, 2);
                gsap.delayedCall(2, () => {
                  moveForklift_z(30, 5);
                  gsap.delayedCall(5, videoForklift);
                });
              });
            });
          });
        });
      });
    });
  });
}

function moveForklift_z(num,time) {
  gsap.to(forklift1.position, {
    z: forklift1.position.z + num,
    duration: time,
    ease: 'power2.inOut',
  });
}

function moveForklift_x(num,time) {
  gsap.to(forklift1.position, {
    x: forklift1.position.x + num,
    duration: time,
    ease: 'power2.inOut',
  });
}

function rotationForklift(num,time){
  gsap.to(forklift1.rotation, {
    z: forklift1.rotation.z + Math.PI / num, // 90 degrees in radians
    duration:time,
    ease: 'power2.inOut',
  });
}

function changeCamera(p_x, p_y, p_z, s_x, s_y, s_z) {
  gsap.to(base.camera.position, {
    x: p_x,
    y: p_y,
    z: p_z,
    duration: 1,
    ease: 'power2',
  });
  gsap.to(base.camera.rotation, {
    x: s_x,
    y: s_y,
    z: s_z,
    duration: 0.5,
    ease: 'power2',
  });
}

function updateMessageArea(picName) {
  if (picName === 'warehousing2.png') {
    changeCamera(73.77, 6.92, -48.87, -3, 0.98, 3.02);
  } else if (picName === 'warehousing1.png') {
    changeCamera(91.25, 12.30, 49.99, -0.17, 0.91, 0.13);
  } else if (picName === 'workshop.png') {
    changeCamera(100, 50, 0, -1.57, 1.11, 1.57);
  }
}

</script>

<style>

#canvasDom {
  display: flex;
  width: 100%;
  height: 100vh;
}
.progress {
  position: absolute;
  z-index: 2;
  left: 45%;
  top: 45%;
}
</style>