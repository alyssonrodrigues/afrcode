<template>
  <li v-on:click="deleteHobby">{{ hobby }}</li>
</template>

<script>
export default {
  name: 'addedhobby',
  props: ['hobby'],
  methods: {
    deleteHobby () {
      this.$emit('hobbyDeleted', this.hobby)
    }
  }
}
</script>
