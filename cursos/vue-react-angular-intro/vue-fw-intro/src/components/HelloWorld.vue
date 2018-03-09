<template>
  <div id="helloworld">
    <input type="text" 
      placeholder="input your name in here..."
      v-model="name" />
    <p>Hello "{{ name }}"!</p>
  </div>
</template>

<<script>
export default {
  data () {
    return {
      name: ''
    }
  }
}
</script>

