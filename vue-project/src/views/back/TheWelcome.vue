<script setup>
import { ref } from "vue";
// 粒子参数
const options = ref({
  background: {
    color: {
      value: '#000'
    },
  },

  fpsLimit: 120,
  interactivity: { // 交互性
    events: { // 事件 
      onClick: { // 1.点击
        enable: true,
        mode: 'push' // "push", "remove", "repulse", "bubble"
      },
      onHover: { // 2.悬停
        enable: true,
        mode: 'grab'  // "grab"(磁吸), "repulse"(排斥), "bubble"(气泡)
      },
      resize: true //调整大小
    },
    modes: {
      // 气泡
      bubble: {
        distance: 400,
        duration: 2, //持续时间
        opacity: 0.8,
        size: 40
      },
      // 推
      push: {
        quantity: 4 //数量
      },
      // 排斥
      repulse: {
        distance: 200,
        duration: 0.4
      }
    }
  },
  // 粒子
  particles: {
    color: {
      value: '#ffffff'
    },
    // 是否用直线连接起来
    links: {
      color: '#ffffff',
      distance: 150,
      enable: true,
      opacity: 0.5,
      width: 1
    },
    // 碰撞
    collisions: {
      enable: true
    },
    // 移动
    move: {
      direction: 'none',
      enable: true,
      // 输出模式
      outModes: {
        default: 'bounce' //弹跳
      },
      random: false, //是否随机
      speed: 4, // 速度
      straight: false //是否直线
    },
    number: {
      // 密度 用value值除以区域值
      density: {
        enable: true,
        area: 1000
      },
      value: 80
    },
    opacity: {
      value: 0.5
    },
    // 形状
    shape: {
      type: 'circle'
    },
    size: {
      value: { min: 1, max: 5 },
    }
  },
  detectRetina: true
}
)


import { loadSlim } from "tsparticles-slim"; 

const particlesInit = async engine => {
    //await loadFull(engine);
    await loadSlim(engine);
};

const particlesLoaded = async container => {
    console.log("Particles container loaded", container);
}
</script>

<template>
  <div>
    <vue-particles id="tsparticles" 
    :particlesInit="particlesInit" 
    :particlesLoaded="particlesLoaded"
    :options="options" />
  </div>
</template>


<style scoped>
#tsparticles {
    position: fixed;  
    z-index: -1;
}
</style>