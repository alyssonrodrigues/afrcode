<template>
  <div id="hobbies">
    <input type="text" v-model="newHobby">
    <button v-on:click="addNewHobby">Add new hobby</button>

    <p v-if="hobbies.length">New hobbies: {{ hobbies.length }}</p>
    <ul>
      <AddedHobby 
        v-for="hobby in hobbies"
        v-bind:key="hobby"
        v-bind:hobby="hobby"
        v-on:hobbyDeleted="deleteHobby($event)"/>
    </ul>

    <p v-if="deletedHobbies.length">Old hobbies: {{ deletedHobbies.length }}</p>
    <ul>
      <DeletedHobby
        v-for="deletedHobby in deletedHobbies"
        v-bind:key="deletedHobby"
        v-bind:hobby="deletedHobby"
        v-on:hobbyReadded="readdHobby($event)"/>
    </ul>
  </div>
</template>

<script>
import AddedHobby from '@/components/AddedHobby'
import DeletedHobby from '@/components/DeletedHobby'

export default {
  name: 'hobbies',
  data () {
    return {
      hobbies: ['Running', 'Dancing', 'Singing'],
      deletedHobbies: [],
      newHobby: ''
    }
  },
  methods: {
    addNewHobby () {
      this.hobbies.push(this.newHobby)
      this.newHobby = ''
    },
    deleteHobby (hobby) {
      var i = this.hobbies.indexOf(hobby)
      this.hobbies.splice(i, 1)
      this.deletedHobbies.push(hobby)
    },
    readdHobby (deletedHobby) {
      this.hobbies.push(deletedHobby)
      var i = this.deletedHobbies.indexOf(deletedHobby)
      this.deletedHobbies.splice(i, 1)
    }
  },
  components: {
    AddedHobby,
    DeletedHobby
  }
}
</script>