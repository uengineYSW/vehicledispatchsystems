<template>
    <v-card outlined>
        <v-card-title>
            DeleteUser
        </v-card-title>

        <v-card-text>
            <String label="RegistrationNumber" v-model="value.registrationNumber" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="deleteUser"
            >
                DeleteUser
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'DeleteUserCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.registrationNumber = '';
        },
        watch: {
        },
        methods: {
            deleteUser() {
                this.$emit('deleteUser', this.value);
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

