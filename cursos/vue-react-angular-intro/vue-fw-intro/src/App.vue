<template>
  <div id="app">
    <router-view/>
    <MainMenu/>
  </div>
</template>

<script>
import MainMenu from '@/components/Menu'

export default {
  name: 'app',
  components: {
    MainMenu
  }
}
</script>
