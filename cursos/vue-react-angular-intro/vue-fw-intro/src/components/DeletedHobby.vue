<template>
  <li v-on:click="readdHobby" style="color: red;">{{ hobby }}</li>
</template>

<script>
export default {
  props: ['hobby'],
  methods: {
    readdHobby () {
      this.$emit('hobbyReadded', this.hobby)
    }
  }
}
</script>
