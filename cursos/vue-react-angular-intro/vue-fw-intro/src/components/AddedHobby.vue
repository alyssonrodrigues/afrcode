<template>
    <li v-on:click="removeHobby">{{ hobby }}</li>
</template>

<script>
export default {
    name: 'addedhobby',
    props: ['hobby'],
    methods: {
        removeHobby() {
            this.$emit('hobbydeleted', this.hobby);
        }
    }    
}
</script>
