<template>

    <v-card outlined>
        <v-card-title>
            UpdateUser
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="RegistrationNumber" v-model="value.registrationNumber" :editMode="editMode"/>
            <String label="DateOfBirth" v-model="value.dateOfBirth" :editMode="editMode"/>
            <String label="Address" v-model="value.address" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateUser"
            >
                UpdateUser
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateUserCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.name = '';
            this.value.registrationNumber = '';
            this.value.dateOfBirth = '';
            this.value.address = '';
        },
        watch: {
        },
        methods: {
            updateUser() {
                this.$emit('updateUser', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

