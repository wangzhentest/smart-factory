<template>
  <el-button style="position: absolute; top: 20px; right: 20px;" @click="drawer = true">
    设置
  </el-button>
  <el-drawer v-model="drawer" title="小车数据" :with-header="false" size="25%">
    <el-card style="max-width: 480px">
      <template #header>巡逻小车</template>
      <img src="/images/car.png" style="width: 100%"/>
      <template #footer>
        <div style="display: flex; align-items: center;">
          <div style="flex: 1;">
            <h3>小车id：car_1</h3>
            <h3>当前速度：5km/s</h3>
            <h3>工作状态：巡逻</h3>
          </div>
          <el-divider direction="vertical" style="height: 60px; border-width: 2px;"></el-divider>
          <div style="flex: 1;">
            <h3> 工厂温度：15℃</h3>
            <h3> 工厂湿度：40%</h3>
            <h3> 工厂噪音：85db</h3>
          </div>
        </div>
      </template>
    </el-card>
    <el-card style="max-width: 480px;margin-top: 20px">
      <template #header>巡逻区域设置</template>
      <div style="display: flex; flex-wrap: wrap;">
        <el-card style="max-width: 160px; margin-right: 10px; margin-bottom: 10px;">
          <img src="/Sprites/WarehouseSlot1.png" style="width: 100%"/>
          <el-switch v-model="value1" />
        </el-card>
        <el-card style="max-width: 160px; margin-right: 10px; margin-bottom: 10px;">
          <img src="/Sprites/WarehouseSlot2.png" style="width: 100%"/>
          <el-switch v-model="value2" />
        </el-card>
        <el-card style="max-width: 160px;margin-right: 10px; margin-bottom: 10px;">
          <img src="/Sprites/WarehouseSlot3.png" style="width: 100%"/>
          <el-switch v-model="value3" />
        </el-card>
        <el-card style="max-width: 160px;margin-right: 10px; margin-bottom: 10px;">
          <img src="/Sprites/WorkPlace.png" style="width: 100%"/>
          <el-switch v-model="value4" />
        </el-card>
      </div>
    </el-card>
  </el-drawer>
  <canvas ref="canvasDom" id="canvasDom"></canvas>
</template>

<script setup>
import * as THREE from 'three'
import Base from '../../utils/Base.js'
import { onMounted, ref, watch} from 'vue'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls'
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader'
import { gsap } from "gsap";
import {useRouter} from 'vue-router';

let base, controls, sprite, car;
let pointTexture, textTexture, spriteMaterial;
const canvasDom = ref(null);
const rayCaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();

const value1 = ref(true)
const value2 = ref(false)
const value3 = ref(false)
const value4 = ref(false)
let selectedSprite = null;
let mixer;
const router = useRouter();
const drawer = ref(false)
onMounted(() => {
  base = new Base(canvasDom.value);
  const axesHelper = new THREE.AxesHelper(0);
  base.scene.add(axesHelper);
  controls = new OrbitControls(base.camera, base.renderer.domElement);
  controls.enablePan = false;
  base.addAmbientLight2();
  createSprite('Sprites/WorkPlace.png', 0, 3, 5, 5, 2, 2);
  createSprite('Sprites/WarehouseSlot1.png', -22, 3, -5, 5, 2, 2);
  createSprite('Sprites/WarehouseSlot2.png', 25, 3, 0, 5, 2, 2);
  createSprite('Sprites/WarehouseSlot3.png', 4, 3, -20, 5, 2, 2);
  loadModel();
  createFence1()
  createFence2()
  createFence3()
  createFence4()
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

function createFence1() {
  const fenceVertices = [
    new THREE.Vector3(-10.5, 0, -29),
    new THREE.Vector3(-10.5, 0, 20),
    new THREE.Vector3(-34, 0, -29),
    new THREE.Vector3(-34, 0, 20),

    new THREE.Vector3(-10.5, 5, -29),
    new THREE.Vector3(-10.5, 5, 20),
    new THREE.Vector3(-34, 5, -29),
    new THREE.Vector3(-34, 5, 20),
  ];

  const fenceIndices = [
    // 四周的面
    0, 4, 1,
    1, 4, 5,
    1, 5, 3,
    3, 5, 7,
    3, 7, 2,
    2, 7, 6,
    2, 6, 0,
    0, 6, 4
  ];

  const fenceGeometry = new THREE.BufferGeometry();
  fenceGeometry.setAttribute('position', new THREE.Float32BufferAttribute(fenceVertices.flatMap(v => [v.x, v.y, v.z]), 3));
  fenceGeometry.setIndex(fenceIndices);

  const fenceMaterial = new THREE.MeshBasicMaterial({ color: 0xccccff, transparent: true, opacity: 0.5, side: THREE.DoubleSide }); // 半透明的红色
  const fenceMesh = new THREE.Mesh(fenceGeometry, fenceMaterial);

  base.scene.add(fenceMesh);
}
function createFence2() {
  const fenceVertices = [
    new THREE.Vector3(-10, 0, -1),
    new THREE.Vector3(-10, 0, 13),
    new THREE.Vector3(14, 0, -1),
    new THREE.Vector3(14, 0, 13),

    new THREE.Vector3(-10, 5, -1),
    new THREE.Vector3(-10, 5, 13),
    new THREE.Vector3(14, 5, -1),
    new THREE.Vector3(14, 5, 13),
  ];

  const fenceIndices = [
    // 四周的面
    0, 4, 1,
    1, 4, 5,
    1, 5, 3,
    3, 5, 7,
    3, 7, 2,
    2, 7, 6,
    2, 6, 0,
    0, 6, 4
  ];

  const fenceGeometry = new THREE.BufferGeometry();
  fenceGeometry.setAttribute('position', new THREE.Float32BufferAttribute(fenceVertices.flatMap(v => [v.x, v.y, v.z]), 3));
  fenceGeometry.setIndex(fenceIndices);

  const fenceMaterial = new THREE.MeshBasicMaterial({ color: 0xffffcc, transparent: true, opacity: 0.5, side: THREE.DoubleSide }); // 半透明的红色
  const fenceMesh = new THREE.Mesh(fenceGeometry, fenceMaterial);

  base.scene.add(fenceMesh);
}
function createFence3() {
  const fenceVertices = [
    new THREE.Vector3(-10, 0, -8.5),
    new THREE.Vector3(-10, 0, -29),
    new THREE.Vector3(14, 0, -8.5),
    new THREE.Vector3(14, 0, -29),

    new THREE.Vector3(-10, 5, -8.5),
    new THREE.Vector3(-10, 5, -29),
    new THREE.Vector3(14, 5, -8.5),
    new THREE.Vector3(14, 5, -29),
  ];

  const fenceIndices = [
    // 四周的面
    0, 4, 1,
    1, 4, 5,
    1, 5, 3,
    3, 5, 7,
    3, 7, 2,
    2, 7, 6,
    2, 6, 0,
    0, 6, 4
  ];

  const fenceGeometry = new THREE.BufferGeometry();
  fenceGeometry.setAttribute('position', new THREE.Float32BufferAttribute(fenceVertices.flatMap(v => [v.x, v.y, v.z]), 3));
  fenceGeometry.setIndex(fenceIndices);

  const fenceMaterial = new THREE.MeshBasicMaterial({ color: 0xffcccc, transparent: true, opacity: 0.5, side: THREE.DoubleSide }); // 半透明的红色
  const fenceMesh = new THREE.Mesh(fenceGeometry, fenceMaterial);

  base.scene.add(fenceMesh);
}
function createFence4() {
  const fenceVertices = [
    new THREE.Vector3(36, 0, -15),
    new THREE.Vector3(36, 0, 5),
    new THREE.Vector3(15, 0, -15),
    new THREE.Vector3(15, 0, 5),

    new THREE.Vector3(36, 5, -15),
    new THREE.Vector3(36, 5, 5),
    new THREE.Vector3(15, 5, -15),
    new THREE.Vector3(15, 5, 5),
  ];

  const fenceIndices = [
    // 四周的面
    0, 4, 1,
    1, 4, 5,
    1, 5, 3,
    3, 5, 7,
    3, 7, 2,
    2, 7, 6,
    2, 6, 0,
    0, 6, 4
  ];

  const fenceGeometry = new THREE.BufferGeometry();
  fenceGeometry.setAttribute('position', new THREE.Float32BufferAttribute(fenceVertices.flatMap(v => [v.x, v.y, v.z]), 3));
  fenceGeometry.setIndex(fenceIndices);

  const fenceMaterial = new THREE.MeshBasicMaterial({ color: 0xccffcc, transparent: true, opacity: 0.5, side: THREE.DoubleSide }); // 半透明的红色
  const fenceMesh = new THREE.Mesh(fenceGeometry, fenceMaterial);

  base.scene.add(fenceMesh);
}

function loadModel() {
  const loader = new GLTFLoader();
  loader.load(
      'models/carview.glb',
      (gltf) => {
        let model = gltf.scene;
        base.scene.add(model);
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
        car = model.getObjectByName("car");
        animationWorkPlace();
      },
  );
}

function createSprite(picName, p_x, p_y, p_z, s_x, s_y, s_z) {
  pointTexture = new THREE.TextureLoader().load('Sprites/point.png');
  spriteMaterial = new THREE.SpriteMaterial({ map: pointTexture });
  sprite = new THREE.Sprite(spriteMaterial);
  sprite.position.set(p_x, p_y - 2, p_z);
  sprite.scale.set(1, 1, 1);
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

function createPointSprite(picName, p_x, p_y, p_z) {
  pointTexture = new THREE.TextureLoader().load('Sprites/point.png');
  spriteMaterial = new THREE.SpriteMaterial({ map: pointTexture });
  sprite = new THREE.Sprite(spriteMaterial);
  sprite.position.set(p_x, p_y - 2, p_z);
  sprite.scale.set(2, 2, 2);
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

async function animationWorkPlace() {
  await rotationRight(2, 2);
  await moveToZ(36, 4);
  await rotationLift(2, 2);
  await moveToX(49,5)
  await rotationLift(2, 2);
  await moveToZ(-8,2)
  await rotationLift(2, 2);
  await moveToX(-22.5,3)
  await rotationLift(2, 2);
  await moveToZ(8, 4);
  await rotationRight(2, 2);
  await moveToX(-26.5,3)
  await rotationRight(2, 2);
  await moveToZ(-36, 4);
  await rotationRight(2, 2);
}

async function animationWarehouseSlot1() {
  await rotationRight(2, 2);
  await moveToZ(41, 5);
  await rotationLift(2, 2);
  await moveToX(9.5,2)
  await rotationLift(2, 2);
  await moveToZ(-5,2)
  await rotationRight(2, 2);
  await moveToX(5,2)
  await rotationLift(2, 2);
  await moveToZ(-36, 4);
  await rotationLift(2, 2);
  await moveToX(-14.5,2)
  await rotationLift(1, 2);
}

function rotationRight(num, time) {
  return new Promise(resolve => {
    gsap.to(car.rotation, {
      z: car.rotation.z - Math.PI / num,
      duration: time,
      ease: 'power2.inOut',
      onComplete: resolve
    });
  });
}
function rotationLift(num, time) {
  return new Promise(resolve => {
    gsap.to(car.rotation, {
      z: car.rotation.z + Math.PI / num,
      duration: time,
      ease: 'power2.inOut',
      onComplete: resolve
    });
  });
}
async function moveToZ(num, time) {
  return new Promise(resolve => {
    gsap.to(car.position, {
      z: car.position.z + num,
      duration: time,
      ease: 'power2.inOut',
      onComplete: resolve
    });
  });
}
async function moveToX(num, time) {
  return new Promise(resolve => {
    gsap.to(car.position, {
      x: car.position.x + num,
      duration: time,
      ease: 'power2.inOut',
      onComplete: resolve
    });
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
  if (picName === 'point1.png') {
    changeCamera(73.77, 6.92, -48.87, -3, 0.98, 3.02);
  }if (picName === 'point2.png') {
    changeCamera(10, 0, -48.87, -3, 0.98, 3.02);
  }
  const messageArea = document.getElementById('messageArea');
  messageArea.textContent = `Message Area: Clicked ${picName}`;
}

</script>

<style>

#canvasDom {
  display: flex;
  width: 100%;
  height: 100vh;
  z-index: 1;
}

#messageArea {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: #ffffff;
  padding: 10px;
  border: 1px solid #000000;
}
.progress {
  position: absolute;
  z-index: 2;
  left: 45%;
  top: 45%;
}

</style>