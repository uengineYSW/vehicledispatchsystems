<template>
    <v-card outlined>
        <v-card-title>
            CreateUser
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
                    color="primary"
                    text
                    @click="createUser"
            >
                CreateUser
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
        name: 'CreateUserCommand',
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
            createUser() {
                this.$emit('createUser', this.value);
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

