<template>
  <div id="app">
    <router-view/>
    <app-menu/>
  </div>
</template>

<script>
import Menu from '@/components/Menu'

export default {
  components: {
    'app-menu': Menu
  }
}
</script>
