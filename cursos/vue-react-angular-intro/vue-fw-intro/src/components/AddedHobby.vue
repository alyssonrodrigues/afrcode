<template>
  <li v-on:click="deleteHobby">{{ hobby }}</li>
</template>

<script>
export default {
  props: ['hobby'],
  methods: {
    deleteHobby () {
      this.$emit('hobbyDeleted', this.hobby)
    }
  }
}
</script>
